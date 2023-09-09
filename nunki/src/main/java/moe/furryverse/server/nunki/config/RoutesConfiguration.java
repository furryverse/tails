package moe.furryverse.server.nunki.config;

import moe.furryverse.server.common.content.Resource;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfiguration {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route for Posts
                .route(Resource.ServiceName.POST_SERVICE, r -> r.path("/api/v0/cluster/**").uri(String.format("lb://%s", Resource.ServiceName.POST_SERVICE)))
                .route(Resource.ServiceName.POST_SERVICE, r -> r.path("/api/v0/galaxy/**").uri(String.format("lb://%s", Resource.ServiceName.POST_SERVICE)))
                .route(Resource.ServiceName.POST_SERVICE, r -> r.path("/api/v0/nucleus/**").uri(String.format("lb://%s", Resource.ServiceName.POST_SERVICE)))

                // Route for Comments
                .route(Resource.ServiceName.COMMENT_SERVICE, r -> r.path("/api/v0/asteroid/**").uri(String.format("lb://%s", Resource.ServiceName.COMMENT_SERVICE)))
                .route(Resource.ServiceName.COMMENT_SERVICE, r -> r.path("/api/v0/comet/**").uri(String.format("lb://%s", Resource.ServiceName.COMMENT_SERVICE)))
                .route(Resource.ServiceName.COMMENT_SERVICE, r -> r.path("/api/v0/planet/**").uri(String.format("lb://%s", Resource.ServiceName.COMMENT_SERVICE)))
                .route(Resource.ServiceName.COMMENT_SERVICE, r -> r.path("/api/v0/stardust/**").uri(String.format("lb://%s", Resource.ServiceName.COMMENT_SERVICE)))

                // Route for File Service
                .route(Resource.ServiceName.FILE_SERVICE, r -> r.path("/api/v0/upload").uri(String.format("lb://%s", Resource.ServiceName.FILE_SERVICE)))
                .route(Resource.ServiceName.FILE_SERVICE, r -> r.path("/api/v0/file/**").uri(String.format("lb://%s", Resource.ServiceName.FILE_SERVICE)))

                .build();
    }
}
