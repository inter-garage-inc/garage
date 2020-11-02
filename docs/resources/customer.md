# Customer Resources

### GET /customers
```json
  [
      {
          "activated": true
      }
  ]
```

### POST /customers
```json
    {
      "activated": true
    }
```

### GET /customers/:id
```json
    {
      "id": 123456,
      "activated": true
    }
```

### PUT /customers/:id
```json
    {
      "id": 123456,
      "activated": false
    }
```

### DELETE /customers/:id
