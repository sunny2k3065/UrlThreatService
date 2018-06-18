package cli;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class EmbeddedServer {

    private static AtomicLong startTime = new AtomicLong(0);

    private ServiceLocator serviceLocator;
    private HttpServer server;
    private ScheduledExecutorService scheduler;

    public EmbeddedServer() {
    }

    private static EmbeddedServer instance = new EmbeddedServer();

    public static EmbeddedServer getInstance() {
        return instance;
    }

    public static long getStartTime() {
        return startTime.get();
    }

    public void run() throws IOException, InterruptedException {
        String message;
        URI uri = UriBuilder.fromUri("http://" + NetworkListener.DEFAULT_NETWORK_HOST).port(parseAndValidatePort()).build();
        HttpServer server = createServer(uri);
        // Ensure the server startup is clean
        if (startServer(server) == false) {
            return;
        } else {

            waitForShutdown();
        }
    }

    private HttpServer createServer(URI uri) throws IOException {
        ResourceConfig application = resourceConfigProvider();
        registerResources(application);

        server = GrizzlyHttpServerFactory.createHttpServer(uri, application, false, null, false);

        return server;
    }

    private boolean startServer(HttpServer server) throws IOException, InterruptedException {
        String message;

        try {
            server.start();
        } catch (IOException be) {
            // Shutdown the server when a bind or unknown exception occurs during its startup
            return false;
        }


        return true;
    }

    protected void waitForShutdown() throws InterruptedException {
        Thread.currentThread().join();
    }

    private int parseAndValidatePort() {
        String portStr = System.getProperty("servicePort", "");
        try {
            int port = Integer.parseInt(portStr);
            if (port < 0 || port > 0xFFFF) {
                throw new IllegalArgumentException("Invalid port: "+ port);
            } return port;
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Bad port: "+ portStr);
        }
    }

    private ResourceConfig resourceConfigProvider() {
        ServiceLoader<ResourceConfig> resourceConfigServiceLoader = ServiceLoader.load(ResourceConfig.class);
        Optional<ResourceConfig> resourceConfig = Optional.ofNullable(null);

        //Assign default resource config.
        resourceConfig = !resourceConfig.isPresent() ?
                Optional.ofNullable((resourceConfigServiceLoader.iterator().next())) :
                resourceConfig;
        resourceConfig.ifPresent(resourceConf -> resourceConf.property(ServerProperties.WADL_FEATURE_DISABLE, true));
        return resourceConfig.get();
    }

    private void registerResources(ResourceConfig application) {
        application.register(JacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);

       /* application.register((Feature) context -> {
            serviceLocator = ServiceLocatorProvider.getServiceLocator(context);
            return false;
        });*/
    }

    public void suspendServer() {
        server.getListeners().stream().forEach(networkListener -> networkListener.pause());
    }

    public void resumeServer() {
        server.getListeners().stream().forEach(networkListener -> networkListener.resume());
    }

}

