# Haxe OpenApi client codegen

This project is at the very beginning so probably nothing works

I am not from the Java world so many things might be done in the wrong way.
Advices/PRs on how to do something right or better much appreciated.


## Useful commands
### Project stub under `core` is generated using following command
`openapi-generator-cli meta -o core/ -n haxe-client-codegen -p com.company.codegen`

`openapi-generator-cli` must be in your PATH in order to execute this command. (See [OpenApi documentation](https://github.com/OpenAPITools/openapi-generator#launcher-script))

See `README.md` inside `core/` to find additional useful commands

To compile a source code from your codegen:
```
java -cp core/target/haxe-client-codegen-openapi-generator-1.0.0.jar:openapi-generator-cli-4.2.2.jar \
    org.openapitools.codegen.OpenAPIGenerator generate -g haxe-client-codegen \
    -i https://raw.githubusercontent.com/openapitools/openapi-generator/master/modules/openapi-generator/src/test/resources/2_0/petstore.yaml \
    -o ./out/myClient \
    -c config.json
```