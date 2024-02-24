//package com.example.VirtualFitON.Configurations;
//
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
//                authorizeRequests().antMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/routeA/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/routeB/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/routeB/**").hasRole("ADMIN").and().
//                requestCache().requestCache(new NullRequestCache()).and().
//                httpBasic().authenticationEntryPoint(authenticationEntryPoint).and().
//                cors().and().
//                csrf().disable();
//    }
//}
