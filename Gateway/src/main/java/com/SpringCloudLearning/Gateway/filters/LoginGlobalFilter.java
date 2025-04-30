package com.SpringCloudLearning.Gateway.filters;

import com.SpringCloudLearning.Gateway.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Order(1)
public class LoginGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getURI().getPath();
        System.out.println();
        System.out.println();
        System.out.println(requestPath);
        System.out.println();
        System.out.println();
        //登录请求直接忽略
        if ("/login".equals(requestPath)) {
            return chain.filter(exchange);
        }
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        //提供了jwt令牌, 且以Bearer开头
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String jwt = authorizationHeader.substring(7); // 去掉 "Bearer " 前缀
            try {
                Claims claims = JwtUtils.parseJwt(jwt);
                //登录过期时间
                Date expiration = claims.getExpiration();
                if (expiration != null && expiration.before(new Date())) {
                    // 如果过期，返回登录过期的响应
                    return handleExpiredResponse(exchange);
                }
                // 如果未过期，放行
                return chain.filter(exchange);
            } catch (Exception e) {
                // 如果解析失败，返回未授权的响应
                return handleUnauthorizedResponse(exchange);
            }

        } else {
            // 如果没有提供令牌，也返回未授权的响应
            return handleUnauthorizedResponse(exchange);
        }
    }
    //未登录处理器
    private Mono<Void> handleUnauthorizedResponse(ServerWebExchange exchange){
        ServerHttpResponse response = exchange.getResponse();
        //未登录状态
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //未登录错误码
        String errorMessage = "{\"error\":\"Unauthorized\",\"message\":\"Invalid token\"}";

        DataBuffer buffer = response.bufferFactory().wrap(errorMessage.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    //登录超时处理器
    private Mono<Void> handleExpiredResponse (ServerWebExchange exchange){
        ServerHttpResponse response = exchange.getResponse();
        //未登录状态
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //登录超时错误码
        String errorMessage = "{\"error\":\"Token expired\",\"message\":\"Your login has expired\"}";

        DataBuffer buffer = response.bufferFactory().wrap(errorMessage.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}