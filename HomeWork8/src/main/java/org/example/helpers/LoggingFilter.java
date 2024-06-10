package org.example.helpers;


import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class LoggingFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
       responseContext.getHeaders().add("Path", requestContext.getUriInfo().getAbsolutePath());
        System.out.println("Path+ "+requestContext.getUriInfo().getAbsolutePath());
       // requestContext.getUriInfo().getAbsolutePath();

    }
}