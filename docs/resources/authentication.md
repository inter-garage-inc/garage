#Authentication Resources

### POST /authentication/create
#### request
```json
{
  "username": "foo",
  "password": "bar"
}
```
##### response
```json
{
  "username": "foo",
  "password": "bar",
  "role": "ADMIN"
}
```