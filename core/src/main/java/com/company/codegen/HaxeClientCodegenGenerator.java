package com.company.codegen;

import org.openapitools.codegen.*;
import io.swagger.models.properties.*;

import java.util.*;
import java.io.File;

public class HaxeClientCodegenGenerator extends DefaultCodegen implements CodegenConfig {

  // source folder where to write the files
  protected String sourceFolder = "src";
  protected String apiVersion = "1.0.0";

  /**
   * Configures the type of generator.
   *
   * @return  the CodegenType for this generator
   * @see     org.openapitools.codegen.CodegenType
   */
  public CodegenType getTag() {
    return CodegenType.CLIENT;
  }

  /**
   * Configures a friendly name for the generator.  This will be used by the generator
   * to select the library with the -g flag.
   *
   * @return the friendly name for the generator
   */
  public String getName() {
    return "haxe-client-codegen";
  }

  @Override
  public Map<String, Object> postProcessModels(Map<String, Object> objs) {
    List<Object> models = (List<Object>) objs.get("models");
    for (Object _mo : models) {
        Map<String, Object> mo = (Map<String, Object>) _mo;
        CodegenModel cm = (CodegenModel) mo.get("model");
        // Set appropriate dataType
        cm.setDataType(typeMapping.get(cm.getDataType()));
    }
    return postProcessModelsEnum(objs);
  }

  /**
   * Provides an opportunity to inspect and modify operation data before the code is generated.
   */
  @SuppressWarnings("unchecked")
  @Override
  public Map<String, Object> postProcessOperationsWithModels(Map<String, Object> objs, List<Object> allModels) {

    // to try debugging your code generator:
    // set a break point on the next line.
    // then debug the JUnit test called LaunchGeneratorInDebugger

    Map<String, Object> results = super.postProcessOperationsWithModels(objs, allModels);

    Map<String, Object> ops = (Map<String, Object>)results.get("operations");
    ArrayList<CodegenOperation> opList = (ArrayList<CodegenOperation>)ops.get("operation");

    // iterate over the operation and perhaps modify something
    for(CodegenOperation co : opList){
      // example:
      // co.httpMethod = co.httpMethod.toLowerCase();
    }

    return results;
  }

  /**
   * Returns human-friendly help for the generator.  Provide the consumer with help
   * tips, parameters here
   *
   * @return A string value for the help message
   */
  public String getHelp() {
    return "Generates a haxe-client-codegen client library.";
  }

  public HaxeClientCodegenGenerator() {
    super();
    outputFolder = "generated-code/haxe-client-codegen";
    modelTemplateFiles.put("model.mustache", ".hx");
    apiTemplateFiles.put("api.mustache", ".hx");
    templateDir = "haxe-client-codegen";
    apiPackage = "org.openapitools.api";
    modelPackage = "org.openapitools.model";
    reservedWords = new HashSet<String> (
      Arrays.asList(
        "package", "import", "haxe", "class", "for", "in", "public", "var", "private", "abstract",
        "Array", "Dynamic", "Int", "Bool", "String", "Float", "if", "else", "throw", "catch",
        "new", "function", "static", "null", "cast", "Reflect", "return", "void", "new", "new",
        "enum", "to", "from", "while", "continue", "break", "true", "false", "Any", "Std",
        "override"
      )
    );
    additionalProperties.put("apiVersion", apiVersion);

    // supportingFiles.add(new SupportingFile("myFile.mustache", "", "myFile.sample"));

    languageSpecificPrimitives = new HashSet<String>(
      Arrays.asList("string", "char", "boolean", "null", "integer", "int", "float", "long", "short", "List", "number", "double", "UUID", "URI", "BigDecimal", "DateTime"));

      typeMapping.clear();
      typeMapping.put("array", "Array");
      typeMapping.put("map", "Map");
      typeMapping.put("List", "Array");
      typeMapping.put("boolean", "Bool");
      typeMapping.put("string", "String");
      typeMapping.put("int", "Int");
      typeMapping.put("float", "Float");
      typeMapping.put("number", "Int");
      typeMapping.put("DateTime", "String");
      typeMapping.put("long", "Int");
      typeMapping.put("short", "Int");
      typeMapping.put("char", "String");
      typeMapping.put("double", "Float");
      typeMapping.put("object", "Dynamic");
      typeMapping.put("integer", "Int");
      typeMapping.put("ByteArray", "Bytes");  // Not sure about this
      typeMapping.put("binary", "Bytes");
      typeMapping.put("file", "File");  // TODO: fix
      typeMapping.put("UUID", "String");
      typeMapping.put("URI", "String");
      typeMapping.put("BigDecimal", "Float");  // Not sure about this
  }
  @Override
  public String escapeReservedWord(String name) {
    return "_" + name;  // add an underscore to the name
  }

  public String modelFileFolder() {
    return outputFolder + "/" + sourceFolder + "/" + modelPackage().replace('.', File.separatorChar);
  }

  @Override
  public String apiFileFolder() {
    return outputFolder + "/" + sourceFolder + "/" + apiPackage().replace('.', File.separatorChar);
  }

  @Override
  public String escapeUnsafeCharacters(String input) {
    return input;
  }

  public String escapeQuotationMark(String input) {
    return input.replace("\"", "\\\"");
  }
}
