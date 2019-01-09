# spring5-reactive
example rxjava and spring5 webflux.

# processing web request

1) request mapping
2) request binding
3) handle data
4) create reponse


# RouterFunction + HandlerFunction 

- Router Fuction is Request mapping 

```
  @FunctionalInterface
  public interface RouterFuction <T extends ServerResponse>{
       Mono<handlerFunction<T>> route(ServerRequest request);
   }
```
- RouterFunction은 함수형 스타일의 요청 매핑(request mapping, request binding)
- HandlerFunction은 함수형 스타일의 웹 핸들러이며, 요청을 받아 응답을 돌려주는 함수 (handle data, create response)


