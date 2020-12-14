package com.cjbooms.fabrikt.cli

enum class CodeGenerationType(val description: String, val requires: Set<InternalCodeGenGenType>) {
    HTTP_MODELS(
        "Jackson annotated data classes to represent the schema objects defined in the input.",
        setOf(InternalCodeGenGenType.MODELS)
    ),
    CONTROLLERS(
        "Spring annotated HTTP controllers for each of the endpoints defined in the input.",
        setOf(
            InternalCodeGenGenType.MODELS,
            InternalCodeGenGenType.SERVICE_INTERFACES,
            InternalCodeGenGenType.CONTROLLERS
        )
    ),
    CLIENT(
        "Simple http rest client.",
        setOf(
            InternalCodeGenGenType.MODELS,
            InternalCodeGenGenType.CLIENT
        )
    );

    override fun toString() = "`${super.toString()}` - $description"
}

// Ordering is important here...
enum class InternalCodeGenGenType {
    MODELS,
    CONTROLLERS,
    SERVICE_INTERFACES,
    CLIENT
}

enum class ClientCodeGenOptionType(private val description: String) {
    RESILIENCE4J("Generates a fault tolerance service for the client using the following library \"io.github.resilience4j:resilience4j-all:1.2.0\"");

    override fun toString() = "`${super.toString()}` - $description"
}

enum class ModelCodeGenOptionType(val description: String) {
    JAVA_SERIALIZATION("This option adds Java Serializable interface to the generated models"),
    QUARKUS_REFLECTION_CONFIG("This options generates the reflection-config.json file for quarkus integration projects");

    override fun toString() = "`${super.toString()}` - $description"
}

data class CodeGenOptions(
    val clientOptions: Set<ClientCodeGenOptionType> = emptySet()
)
