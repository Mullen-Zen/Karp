package com.karp;

enum TokenType {
    // Single-character tokens
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE, 
    COMMA, DOT, MINUS, PLUS, PIPE, SLASH, STAR, QUESTION,

    // One or two character tokens
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    // Literals
    IDENTIFIER, STRING, NUMBER,

    // Keywords
    AND, CLASS, ELSE, FALSE, FUNC, FOR, IF, NIL, 
    OR, SAY, REPLY, SUPER, THIS, TRUE, VAR, WHILE,

    EOF
}

/*
Note here that the following values have been changed from the original presented in the textbook
  because I'm building Karp, not Lox. Here are the changes:

  Single character tokens:
    'semicolon' -> 'pipe' (| is used instead of ;)
    '' -> 'question' (?? signifies comments)

  One or two character tokens:
    no changes

  Literals:
    no changes

  Keywords:
    'fun' -> 'func' (functions are declared with func instead of fun)
    'print' -> 'say' (statements are printed with say)
    'return' -> 'reply' (functions return values with reply)
*/