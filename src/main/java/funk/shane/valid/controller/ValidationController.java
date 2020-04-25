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

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import funk.shane.valid.pojo.Person;
import funk.shane.valid.util.Utils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import lombok.extern.slf4j.Slf4j;

@Controller("/api")
@Slf4j
public class ValidationController {
    
    /**
     * validDemo - REST POST call to "write" a person to a data source (not provided in this demo app)
     * Only returns a String with either an affirmative valid message or explains why the input was incorrect
     * 
     * @param person
     * @return
     */
    @Post(uri = "/v1/valid", produces = {MediaType.TEXT_PLAIN})
    public String validDemo(@Valid @Body Person person) {
        log.info("inbound person: {}", person);

        return String.format("Micronaut Person [%s] was valid", person);
    }

    /**
     * Retrieve a person - even though there's an id sent in, this trivial example doesn't pull from a data source 
     * in this version
     * @return
     */
    @Get(uri = "/v1/get-a-person/{id}", produces = {MediaType.APPLICATION_JSON})
    public Person getPerson(@PathVariable String id) {
        log.info("Get Person with id: [{}]", id);
        final Person person = Utils.getClassFromJsonResource(Person.class, "person-1.json");
        log.info("Returning Micronaut person: {}", person);
        return person;
    }

    /**
     * validationErrorHandler - using Micronaut to provide clean information about why a Person object was invalid
     * https://docs.micronaut.io/latest/guide/index.html#_local_error_handling
     * @param httpRequest
     * @param violationException
     * @return Bad Request Status code with list of invalid data
     */
    @Error
    public HttpResponse<String> validationErrorHandler(HttpRequest<?> httpRequest, ConstraintViolationException violationException) {
        final List<String> validationStrings = violationException.getConstraintViolations().stream()
        .map(v -> v.getMessage())
        .sorted()
        .collect(Collectors.toList());

        log.error("Inbound REST call tripped validation error(s) [{}]", validationStrings.toString());

        return HttpResponse.<String>status(HttpStatus.BAD_REQUEST, "Invalid data was POSTed").body(validationStrings.toString());
    }
}