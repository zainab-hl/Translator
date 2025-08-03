
This project is a **RESTful API service** built with **Jakarta EE** that translates text from **Latin-based languages** to **Moroccan Darija** (Arabic dialect).  
It uses **Google's Gemini 1.5 Flash** for natural language processing and translation.

Generated with the [Eclipse Foundation Jakarta EE Starter](https://start.jakarta.ee/).


**Technologies**

- Jakarta EE 10
- Java SE 17
- RESTful Web Services (JAX-RS)
- Maven Wrapper
- Gemini 1.5 Flash API (Google AI)
- Deployable on any Jakarta EE compatible server (WildFly, GlassFish, Payara, etc.)

---

**Build Instructions**

Make sure you have Java 17 installed.  
Clone the repository, navigate to the project folder, and run:

```
cmd.exe
mvnw.cmd clean package
```
This will generate a WAR file:  target/jakartaee-hello-world.war
Deploy this file to your Jakarta EE runtime (e.g., WildFly).

Usage Example
Once the service is running (e.g., on http://localhost:8080/jakartaee-hello-world), you can send a request like this:

You can use Postman or curl to test the /api/translate endpoint.

```
curl -X POST http://localhost:8080/jakartaee-hello-world/api/translate \
  -H "Content-Type: application/json" \
  -d '{
    "text": "Salve! Quid agis?"
}'
```
then you'll get a .json response like this : 
{
  "response": "سلام! كيداير؟"
}
