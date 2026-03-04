How to design a REST API - 

Clarification       : Start by defining entities (nouns) and user actions to avoid building unnecessary features.
Resource Modeling   : Use plural nouns for endpoints (e.g., /users) and avoid using verbs in the URL path.
HTTP Methods        : Map CRUD to standard verbs: POST (create), GET (read), PUT/PATCH (update), and DELETE (remove).
Status Codes        : Return 2xx for success, 4xx for client mistakes, and 5xx for server failures to ensure predictable error handling.
Production Features : Implement versioning (/v1), pagination for large lists, and JWT for secure authentication.

