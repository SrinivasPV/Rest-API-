Feature: Testing the Library API

Scenario Outline: Add Book to Library API
Given User get the body for adding book
When calling <api> to add the book using <method>
Then Checking the status code <code>
And Verifying the message <msg>
And "getBookAPI" book details using "GET"

Examples:
|api|code|msg|method|
|addBookAPI|200|successfully added|POST|

Scenario Outline: Delete Book to Library API
Given User get the body for deleting the book
When calling <api> to add the book using <method>
Then Checking the status code <code>
And Verifying the message <msg>

Examples:
|api|code|msg|method|
|deleteBookAPI|200|book is successfully deleted|POST|