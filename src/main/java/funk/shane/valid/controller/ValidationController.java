/**
 Copyright 2019 Michael Shane Funk - Javaseeds Consulting

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in
 the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies 
 of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,  
 WHETHER  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package funk.shane.valid.controller;

import javax.validation.Valid;

import funk.shane.valid.pojo.Person;
import funk.shane.valid.util.Utils;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import lombok.extern.slf4j.Slf4j;

@Controller("/api")
@Slf4j
public class ValidationController {
    
    @Post(uri = "/v1/valid", produces = {MediaType.TEXT_PLAIN})
    public String validDemo(@Valid @Body Person person) {
        log.info("inbound person: {}", person);

        return String.format("Person [%s] was valid", person);
    }

    @Get(uri = "/v1/get-a-person", produces = {MediaType.APPLICATION_JSON})
    public Person getPerson() {
        return Utils.getClassFromJsonResource(Person.class, "person-1.json");
    }
}