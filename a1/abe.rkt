#lang plai

;; ABE: Arithmetic Boolean Expressions:
;;
;;  <ABE> ::= <num>
;;         | {+ <ABE> <ABE>}
;;         | {- <ABE> <ABE>}
;;         | true
;;         | false
;;         | {if ABE ABE ABE}
 
;; Abstract syntax of arithmetic expressions:
(define-type ABE
  [Num (n number?)]
  [Add (lhs ABE?) (rhs ABE?)]
  [Sub (lhs ABE?) (rhs ABE?)]
  [True]
  [False]
  [If (pred ABE?) (conseq ABE?) (altern ABE?)])


;; Values resulting from evaluation:
(define-type Value
  [NVal (n number?)]
  [TVal]
  [FVal])


;; Types
(define-type Type
  [Int]
  [Bool])

;; SExp -> ABE
;; Produce an abstract syntax tree for the given S-expression
;; Effect: raises an error if the input is not a well-formed expression
(define (parse-abe sexp)
  (cond
    [(number? sexp) (Num sexp)]
    [(eq? sexp 'true) (True)]
    [(eq? sexp 'false) (False)]
    [(list? sexp)
     (case (first sexp)
       [(+) (if (= (length sexp) 3)
               (Add (parse-abe (second sexp)) (parse-abe (third sexp)))
               (error "parse-abe: + needs exactly 2 arguments"))]
       [(-) (if (= (length sexp) 3)
               (Sub (parse-abe (second sexp)) (parse-abe (third sexp)))
               (error "parse-abe: - needs exactly 2 arguments"))]
       [(if) (if (= (length sexp) 4)
                (If (parse-abe (second sexp))
                    (parse-abe (third sexp))
                    (parse-abe (fourth sexp)))
                (error "parse-abe: if needs exactly 3 arguments"))]
       [else (error "parse-abe: bad expression:" sexp)])]
    [else (error "parse-abe: bad expression:" sexp)]))

;; Require a particular type of result


;; Value -> Number
;; coerce the given value to a number
;; Effect: raises an error if the argument does not represent a number
(define (to-num v)
  (type-case Value v
    [NVal (n) n]
    [TVal () (error "to-num: Bad number:" v)]
    [FVal () (error "to-num: Bad number:" v)]))


;; Value -> Boolean
;; coerce the given value to a Boolean
;; Effect: raises an error if the argument does not represent a boolean
(define (to-bool v)
  (type-case Value v
    [NVal (n) (error "to-bool: Bad Boolean:" v)]
    [TVal () true]
    [FVal () false]))

;; ABE -> Value
;; produce the value corresponding to interpreting the given ABE
;; Effect: raises an error in case of type mismatch
(define (interp abe)
  (type-case ABE abe    
    [Num (n) (NVal n)]
    [Add (abe1 abe2) (NVal (+ (to-num (interp abe1)) (to-num (interp abe2))))]
    [Sub (abe1 abe2) (NVal (- (to-num (interp abe1)) (to-num (interp abe2))))]
    [True () (TVal)]
    [False () (FVal)]
    [If (abe1 abe2 abe3)
        (if (to-bool (interp abe1))
            (interp abe2)
            (interp abe3))]))

;; ABE -> Type
;; produce the static type of the given ABE.
;; Effect: raises an error if the ABE cannot be typed.
(define (type-of abe)
  (type-case ABE abe    
    [Num (n) (Int)]
    [Add (abe1 abe2)
         (let ([t1 (type-of abe1)]
               [t2 (type-of abe2)])
           (if (and (equal? t1 (Int)) (equal? t2 (Int)))
               (Int)
               (error "type-of: argument type mismatch for +" t1 t2)))]
    [Sub (abe1 abe2)
         (let ([t1 (type-of abe1)]
               [t2 (type-of abe2)])
           (if (and (equal? t1 (Int)) (equal? t2 (Int)))
               (Int)
               (error "type-of: argument type mismatch for -" t1 t2)))]
    [True () (Bool)]
    [False () (Bool)]
    [If (abe1 abe2 abe3)
        (let ([t1 (type-of abe1)]
              [t2 (type-of abe2)]
              [t3 (type-of abe3)])
          (if (equal? t1 (Bool))
              (if (equal? t2 t3)
                  t2
                  (error "type-of: type mismatch for if branches" t2 t3))
              (error "type-of: non-Bool predicate type:" t1)))]))
