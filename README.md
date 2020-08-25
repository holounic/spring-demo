# Spring Intro

## Application entry point 
"Main" class of the Spring app is annotated with the ``@SpringBootApplication`` 
annotation. It has the standard Java ``main`` function. Its directory will 
be scanned automatically to determine the presence of other Spring components 
(for example, beans or controllers). There is a way to scan other directories. 

_Take a look at: DemoApplication.java_

## RestController
All classes, which have requests handling functions, in Spring should be annotated as ``@Controller`` or ``@RestController``.
The key difference between them is that ``@RestController`` is a mixture of the ``@Controller``
and the ``@ResponseBody``. Meaning that it is not required to specify ``@ResponseBody``
annotation for the inner functions.

_Take a look at: ShopController.java, CafeController.java, CashierController.java_

## Mappings
In this simple applications I used only two types of mapping -- ``@GetMapping`` 
and ``@PostMapping``, which handle get and post requests accordingly. The target
path is specified in the round brackets. For example, ``@PostMapping("/post")``.
Mapping functions can have arguments, which represent the json request body or 
``@PathVariable`` arguments, which will be extracted from the target path.

_Take a look at: showCat function in ShopController.java_

## Plain Old Java Object Concept
Spring provides the functionality to work with standard java object instead of
non-java ones (jsons, for example). No library is needed to convert json to java class 
(and in  a reversed order).
The only requirement is that the Java class should have public fields or
public getters (for ``@RequestBody``) and setters (for ``@ResponseBody``) 
and the fields of the request json should have the same names as the POJO class.
Let's consider an example.

Spring parses jsons with fields that don't appear in the POJO 
representation properly, just by skipping these unrepresented fields. 

````
public class Date {
    public int day;
    public int month;
    public int year;
    public boolean isMonday;
}
````
````
{"day": 4, "month": 4, "year": 2020, "isMonday": 0}
````

_Take a look at: sellCat functions in ShopController.java and script.js_

## Beans and Inversion of Control
It's really hard to explain them so I'll delegate this task to the authors of these 
two web-pages:

[Beans](https://www.baeldung.com/spring-bean)

[Beans scopes](https://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch04s04.html)


In this project I used ``@ApplicationScope`` bean. It is something similar to 
singleton (the object is shared between different servlets of the Spring application)
but it could be mocked. [More about spring testing](https://www.baeldung.com/spring-boot-testing).

A bean object is marked as a ``@Component``. A function which returns this components
is marked as ``@Bean("name of the bean")``. A class with this function is marked 
as ``@Configuration``.

```@Resource(name = "beanName")``` annotation is used to access an ``@ApplicationScope`` bean 
with the name "beanName".

_Take a look at: Cashier.java, CashierConfig.java, 
CashierController.java, CafeConfig.java_

