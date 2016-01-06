Using the Enable dictionary found at [The National Puzzlers' League](http://www.puzzlers.org/pub/wordlists/enable1.txt).
Removed entries with fewer than 3 characters.

    curl http://www.puzzlers.org/pub/wordlists/enable1.txt | grep -E -e "...+" > boggle.dict

Grabbed the CSS from Shane da Silva's [Boggle Solver](https://github.com/sds/boggle-solver) to get me going.

[AngularJS](https://angularjs.org/) had a nice tutorial and reference to get me going after taking a look at this
[article](https://spring.io/blog/2015/01/12/spring-and-angular-js-a-secure-single-page-application) on using it with
[Spring Boot](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

The application was initially generated with [Spring Iinitializr](https://start.spring.io/)

[Rules](https://en.wikipedia.org/wiki/Boggle) for Boggle. It wouldn't be difficult to include the total possible score
for a given board.


