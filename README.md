**IMPORTANT**
- Gateway is disabled by default for security reason
- Contact Readers team for gateway test

# the-bookclub-api
- This API has the following AWS lambda functions:

### searchBooks:
###### https://croxqcg4a2.execute-api.eu-west-2.amazonaws.com/search/books/{param}
- pass search parameter (isbn, book name, authors) as string, return book list.
- output:
```
[{"isbn":"9798642851920","qty":0,"bookName":"\"Alice's Adventures in Wonderland Annotated and Illustrated Book for Children\"","bookAuthors":"\"Lewis Carroll\"","postCode":null,"thumbnail":"","bookDescription":""},{"isbn":"9781782011057","qty":0,"bookName":"\"Elucidating Alice: A Textual Commentary on Alice's Adventures in Wonderland\"","bookAuthors":"\"Lewis Carroll\"","postCode":null,"thumbnail":"\"https://covers.openlibrary.org/b/id/8788680-M.jpg\"","bookDescription":""}]
```

### testStock:
###### https://croxqcg4a2.execute-api.eu-west-2.amazonaws.com/test/stock/
- default output of a single stock
```
[{"isbn":"9780001006874","qty":0,"bookName":"\"Charlie and the chocolatefactory\"","bookAuthors":"\"Roald Dahl\"","postCode":null,"thumbnail":"\"https://covers.openlibrary.org/b/id/9382835-M.jpg\"","bookDescription":""},{"isbn":"9781417786091","qty":0,"bookName":"\"Charlie and the Chocolate Factory                            Puffin Modern Classics Prebound\"","bookAuthors":"\"Roald Dahl\"","postCode":null,"thumbnail":"\"https://covers.openlibrary.org/b/id/7615488-M.jpg\"","bookDescription":""}]
```

### testInput:
###### https://croxqcg4a2.execute-api.eu-west-2.amazonaws.com/test/input/Hello World
- output: Hello World
