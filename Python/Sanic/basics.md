# Creating sanic app

```python
from sanic import Sanic
from sanic.response import json

app = Sanic("app_name")
@app.route("/")
async def test(request)
    return json({"hello": "world"})

if __name__ == "__main__":
    app.run(host="0.0.0.0", port="8000")
```

# Configuring 
[Source](https://sanic.readthedocs.io/en/latest/sanic/config.html)

- All the configuration lives in the app.config attribute. app.config is a dictionary.

```python
app.config.DB_NAME = 'db_user'

db_setting = {
    'DB_HOST': 'localhost',
    'DB_NAME': 'db_name'
}

app.config.update(db_setting)
```

# Request Data 
[Soucre](https://sanic.readthedocs.io/en/latest/sanic/request_data.html)

- Getting attributes from JSON request

```python
def func(request):
    json = request.json # dictionary
```

- Getting URL queries

```python
queries = request.args 
# {'arg1': ['value1', 'value2']}

queries = request.query_string 
# unparsed string

queries = request.query_args # list
# [['key1', 'value'], ['key2', 'value']]
```

- Getting attributes from form request

```python
form_data = request.form

user = request.form.get('name')
```

