<?xml version="1.0"?>
<recipe>
    <merge from="ApiMethods.java.sdw" to="ApiMethods.java" />
    <merge from="ApiService.java.sdw" to="ApiService.java" />
    <merge from="RequestClient.java.sdw" to="RequestClient.java" />

    <instantiate from="Params.java.sdw"
      to="engine/params/{{params}}.java" />

    <instantiate from="Response.java.sdw"
      to="response/{{response}}.java" />

</recipe>
