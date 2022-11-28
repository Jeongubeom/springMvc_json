package com.sparta.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request")
public class HelloRequestController {

    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }

    @GetMapping("/star/{name}/age/{age}")
    @ResponseBody
    public String helloRequestPath(@PathVariable String name, @PathVariable int age)
    {
        return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
    }

    @GetMapping("/form/param")
    @ResponseBody
    public String helloGetRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
        //url에 값이 넘어감, @RequestParam으로 값을 받을 수 있음
    }

    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
        //url에 값이 넘어가지 않음 @RequestParam으로 값을 받을 수 있음
    }


    @PostMapping("/form/model")
    @ResponseBody
    public String helloRequestBodyForm(@ModelAttribute Star star) {
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }

    //@RequestParam vs @ModelAttribute
    //@RequestParam 하나씩 입력받음 String name, int age 하나씩 입력 받아서 데이터 전송

    //@ModelAttribute 이것은 Star star이렇게 객체 형식으로 값을 송수신함
    //결국 값을 return할때도 star.name, star.age 이렇게 객체에 접근해서 필드 값을 받아와야함
    //그리고 @ModelAttribute을 사용할때 Star의 class 위에 @Setter를 생성해줘야 함.
    //@ModelAttribute 이것은 생략이 가능한데, 나는 지금 초짜니까 생략하지 않고 그냥 쓰는 걸로하자

    @PostMapping("/form/json") //json형식으로 데이터 송수신
    @ResponseBody
    public String helloPostRequestJson(@RequestBody Star star) {
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }
    //json형태로 데이터를 송수신할 때는 @RequestBody이것을 사용해야하고, 객체를 그대로 선언해서 값을 받을 수 있다.
    //하지만 @ModelAttribute와 같이 해당 값을 클라이언트에 던져 줄때는 star.name, star.age 이렇게 보내주어야 한다.
    //만들어 놓은 객체 그러니까 여기서는 star class의 필드 데이터 타입과 클라이언트 측의 데이터 타입이 같아야한다.
}