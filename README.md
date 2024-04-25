# Challenge Instructions
# DATABASE H2
# TOKEN AUTHORIZATION JWT
# PASSWORD AND EMAIL WITH REGEX FILTER AND NOT NULL (EDITABLE)
# ONE PERSON ALLOWED PER REGISTRATION (CHECKED BY EMAIL AND USERNAME)

# RUN APPLICATION
- gradle bootrun

# API REST
- http://localhost:8080/api/v1/user

# ENDPOINT SIGNUP
- POST Request
- JSON Format
- http://localhost:8080/api/v1/user/signup

# REQUEST BODY DATA
{
"username": "Marco Demergasso",
"email": "marco@gmail.com",
"password": "g2kgfGf4",
"phones": [{
"number": "1234567",
"cityCode": "9",
"countryCode": "30"
}]
}
