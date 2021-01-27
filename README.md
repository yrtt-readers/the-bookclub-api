**IMPORTANT**
- Gateway is disabled by default for security reason
- Contact Readers team for gateway test

# the-bookclub-api
- This API has the following AWS lambda functions:

### searchBooks:
###### https://uu7wl2im1i.execute-api.eu-west-2.amazonaws.com/search/books/{param}
- pass search parameter (isbn, book name, authors) as string, return book list.
- output:
```
[{"isbn":"9780689853944","qty":0,"bookName":"\"Red Dog\"","bookAuthors":"\"Bill Wallace\"","postCode":null,"thumbnail":"\"https://covers.openlibrary.org/b/id/437328-M.jpg\"","bookDescription":""}]
```

### getStocks:
###### https://uu7wl2im1i.execute-api.eu-west-2.amazonaws.com/get/stocks/{param}
- pass isbn as string, return stock list in database.
- input: 9780689853944
- output:
```
[{"isbn":"9780689853944","qty":0,"bookName":"\"Red Dog\"","bookAuthors":"\"Bill Wallace\"","postCode":null,"thumbnail":"\"https://covers.openlibrary.org/b/id/437328-M.jpg\"","bookDescription":""}]
```

### setStocks:
###### https://uu7wl2im1i.execute-api.eu-west-2.amazonaws.com/set/stocks/{param}
- pass stocks json, update to database.

### testStock:
###### https://uu7wl2im1i.execute-api.eu-west-2.amazonaws.com/test/stock/{param}
- default output of a single stock
```
[{"isbn":"9780689853944","qty":0,"bookName":"\"Red Dog\"","bookAuthors":"\"Bill Wallace\"","postCode":null,"thumbnail":"\"https://covers.openlibrary.org/b/id/437328-M.jpg\"","bookDescription":""}]
```

### testInput:
###### https://uu7wl2im1i.execute-api.eu-west-2.amazonaws.com/test/input/Hello World
- output: Hello World