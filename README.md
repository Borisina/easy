This project is a RESTful API for a simple social media application using Spring Boot, Hibernate, and PostgreSQL. 
Here you can sign up and sign in as a user, create and view posts, follow other users, and like posts.

Here's the list of actions and endpoints users need to know to interact with your application:

1. Signup: The application has the /auth/signup API, which is a POST operation that does not require authentication. The user sends a request to this API with details for a new account in the request body e.g., {"username":"user", "password":"password"}. If the username is not taken, the endpoint creates a new user and returns HTTP status 201 (CREATED), and the response body will be "User created!". If the username is already taken, it returns HTTP status 409 (Conflict), and the response body will be "Username is already taken!"

2. Authentication: After the user has created an account, they can authenticate using Basic Auth (username and password) with any API that requires authentication. The client typically sends an Authorization header with a 'Basic ' prefix followed by username:password base64 encoded.

3. Create a post: Authenticated users can create a new post by sending a POST request to the endpoint /posts. The request body contains the post data. The request must include Basic Auth credentials.

4. Get user posts: To get specific user's posts, users can send a GET request to /users/{userId}/posts. Replace {userId} with the user's ID.

5. Like a post: Authenticated users can like a post by sending a POST request to /posts/like/{postId} endpoint, with Basic Auth credentials.

6. Follow another user: Authenticated users can follow another user by sending a POST request to /follow/{followId} endpoint, where {followId} is the id of the user to be followed.

7. Get a user's detail: For user details, send a GET request to /users/{id}, where {id} is the id of the user whose details you want to fetch.

8. Keep in mind the application is a RESTful API service that returns JSON-formatted responses; you probably won't see any HTML views.


Feedback:
1. Was it easy to complete the task using AI? 
 - Yes, it was much easier.
2. How long did task take you to complete? 
 - I spent 4 hours to complete this task.
3. Was the code ready to run after generation? What did you have to change to make it usable?
- Only code for tests and Spring Security configuration had little mistakes.
4. Which challenges did you face during completion of the task?
- I faced some challenges related to JaCoCo coverage. I used Lombok and JaCoCo thought that methods hashCode() and equals() were untested.
Eventually I created lombok.config file. Also I had some problems with json serialization, but I fixed it using @JsonIdentityInfo and @JsonIdentityReference.
5. Which specific prompts you learned as a good practice to complete the task?
- I didn't use any specific prompts. All my prompts were pretty simple.