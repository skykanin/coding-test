# coding-test
For this online coding test I have solved task 1 and 2a) in Clojure.

## Installation
To be able to run this code you will need to install [Clojure](https://clojure.org/guides/getting_started#_clojure_installer_and_cli_tools) version 1.10.1 or later.
Lastly you will also need to install the [Leiningen](https://leiningen.org/#install) build tool.

## Running the code and tests
- To start the REST API server you simply run `lein run` which will open a server on `localhost:3008`.
- The unit tests can be executed by simply running `lein test`

### How do I use the rest api?
The endpoint for sending a POST request is `/calc`. The body of the POST request must contain an application/json object with the key
"expression" and value string containing the mathematical expression to be calculated. For example:
```
{"expression": "-1 * (3 + 2)"}
```
In the mathematical expression string all infix operators must have spaces between them. On the other hand parethesis can either have or not have spaced between them. Any negative numbers must be written without spaces. The Rest Api will handle expressions with unbalanced parens or empty expressions.
```
// Valid
{"expression": "8 / (1 + 3)"}

// Valid
{"expression": "8 / ( 1 + 3 )"}

// Valid
{"expression": "-8 / (1 + 3)"}

// Invalid
{"expression": "8/(1+3)"}

// Invalid
{"expression": "8 / (1 + 3))"}

// Invalid
{"expression": "- 8 / (1 + 3)"}

```

## Where can I find the code for the specific tasks?
- The solution for task 1 can be found in the `coding-test.product` namespace and the unit tests can be found in `coding-test.product-test`.
- The solution for task 2 can be found under the `coding-test.calculator.*` namespaces and the unit tests can be found under `coding-test.calculator.route-test`
