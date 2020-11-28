import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
	id("org.springframework.boot") version "2.2.9.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "com.droissor"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

ktlint {
	this.version.set("0.37.0")
	debug.set(false)
	verbose.set(true)
	outputToConsole.set(true)
	coloredOutput.set(true)
	outputColorName.set("RED")
	ignoreFailures.set(true)
	reporters {
		reporter(ReporterType.CHECKSTYLE)
		reporter(ReporterType.JSON)
		reporter(ReporterType.HTML)
	}
	filter {
		exclude("**/style-violations.kt")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
