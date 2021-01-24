# the-bookclub-api
- This API has the following AWS lambda functions:

## getStocks:
- pass isbn as string, return stock list.
https://uu7wl2im1i.execute-api.eu-west-2.amazonaws.com/get/stocks/{param}
input: 9780689853944
output:
```
[{"isbn":"9780689853944","qty":0,"bookName":"\"Red Dog\"","bookAuthors":"\"Bill Wallace\"","postCode":null,"thumbnail":"\"https://covers.openlibrary.org/b/id/437328-M.jpg\"","bookDescription":""}]
```

## setStocks:
- pass stocks json, update to database.
https://uu7wl2im1i.execute-api.eu-west-2.amazonaws.com/set/stocks/{param}

## searchBooks:
- pass search parameter as string, return book list.
https://uu7wl2im1i.execute-api.eu-west-2.amazonaws.com/search/books/{param}

## testStock:
https://uu7wl2im1i.execute-api.eu-west-2.amazonaws.com/test/stock/{param}
- default output:
```
{"isbn":"9780689853944","qty":0,"bookName":"\"Red Dog\"","bookAuthors":"\"Bill Wallace\"","postCode":null,"thumbnail":"\"https://covers.openlibrary.org/b/id/437328-M.jpg\"","bookDescription":""}
```

## testInput:
https://uu7wl2im1i.execute-api.eu-west-2.amazonaws.com/test/input/{param}
- output: value in {param}

**IMPORTANT**
- Gateway is disabled by default for security reason
- Contact Readers team for gateway test

