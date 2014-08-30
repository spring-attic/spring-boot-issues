# Sample application

https://github.com/spring-projects/spring-boot/issues/1370

Steps:

1) Run with spring-boot-starter-parent 1.1.4-RELEASE - works
2) Run with spring-boot-starter-parent 1.1.5-RELEASE - NPE
    a) Remove @EnableWebSecurity - works
    b) Add @EnableWebSecurity, Remove configure(AuthenticationManagerBuilder auth) - works