import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jjwtversion:String = "0.11.5"
val okHttpVersion :String = "4.10.0"
plugins {
	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.fanxan"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.litote.kmongo:kmongo:4.6.0")
	implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

//	Jwt
	implementation("io.jsonwebtoken:jjwt-api:$jjwtversion")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtversion")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtversion")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


tasks.getByName<Jar>("jar"){
	enabled = false
}