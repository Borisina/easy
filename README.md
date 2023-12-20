This project is a RESTful API for a simple social media application using Spring Boot, Hibernate, and PostgreSQL. 
Here you can create users, create and view posts, follow other users, and like posts

Here's the list of actions and endpoints users need to know to interact with your application:

1. Create a new User:
Send a POST request to /users with a JSON payload containing username. The server will return the created User with id.
2. Get User By ID:
Send a GET request to /users/{id} where {id} is the ID of the User. The server will return the User associated with this ID.
3. Create a Post by a User:
Send a POST request to users/{userId}/posts where {userId} is the ID of the User with a JSON payload containing the post details. The server will return the created Post with id.
4. Get all Posts by a User:
Send a GET request to /users/{userId}/posts where {userId} is the ID of the user. The server will return a list of Posts created by the User.
5. Follow another User:
Send a POST request to /users/{id}/follow/{followId} where {id} is the ID of the User who wants to follow and {followId} is the ID of the User to be followed.
6. Like a Post:
Send a POST request to /users/{id}/like/{postId} where {id} is the ID of the User who is liking the post, and {postId} is the ID of the Post being liked.
7. You can make these HTTP requests through various methods - browsers (for GET requests), command-line tools like curl, or GUI platforms such as Postman.

Keep in mind the application is a RESTful API service that returns JSON-formatted responses; you probably won't see any HTML views.


Feedback:
1. Was it easy to complete the task using AI? 
 - Yes, it was much easier.
2. How long did task take you to complete? 
 - I spent 4 hours to complete this task.
3. Was the code ready to run after generation? What did you have to change to make it usable?
- Only code for tests was with little mistakes.
4. Which challenges did you face during completion of the task?
- I faced some challenges related to JaCoCo coverage. I used Lombok and JaCoCo thought that methods hashCode() and equals() were untested.
Eventually I created lombok.config file. Also I had some problems with json serialization, but I fixed it using @JsonIdentityInfo and @JsonIdentityReference.
5. Which specific prompts you learned as a good practice to complete the task?
- I didn't use any specific prompts. All my prompts were pretty simple.