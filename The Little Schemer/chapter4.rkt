#lang racket

(define add1
  (lambda (n)
    (+ n 1)))

(define sub1
  (lambda (n)
    (- n 1)))

(define o+
  (lambda (n m)
    (cond ((zero? n) m)
          ((zero? m) n)
          (else (o+ (sub1 n) (add1 m))))))

;; (o+ 3 4)

(define o-
  (lambda (n m)
    (cond ((zero? n) m)
          ((zero? m) n)
          (else (o- (sub1 n) (sub1 m))))))

;;(o- 14 3)

(define addtup
  (lambda (tup)
    (cond ((null? tup) 0)
          (else (o+ (car tup) (addtup (cdr tup)))))))

;; (addtup '(1 2 3 4 5))

(define *
  (lambda (n m)
    (cond ((zero? n) 0)
          (else (o+ m (* (sub1 n) m))))))

;; (* 3 4)

(define tup+
 (lambda (tup1 tup2)
   (cond ((null? tup1) tup2)
         ((null? tup2) tup1)
         (else (cons (o+ (car tup1) (car tup2))
                     (tup+ (cdr tup1) (cdr tup2)))))))

(tup+ '(3 6 9 11 4) '(8 5 2 0))


         
