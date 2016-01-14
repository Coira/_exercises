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

;; (tup+ '(3 6 9 11 4) '(8 5 2 0))

(define >
  (lambda (n m)
    (cond ((zero? n) #f)
          ((zero? m) #t)
          (else (> (sub1 n) (sub1 m))))))

;;(> 11 11)

(define <
  (lambda (n m)
    (cond ((zero? m) #f)
          ((zero? n) #t)
          (else (< (sub1 n) (sub1 m))))))

;;(< 11 3)
;;(< 3 11)
;;(< 11 11)

(define =
  (lambda (n m)
    (cond ((< n m) #f)
          ((> n m) #f)
          (else #t))))

(define expt
  (lambda (n m)
    (cond ((= m 1) n)
          ((= m 0) 1)
          ((= n 0) 0)
          (else (* n (expt n (sub1 m)))))))

(define length
  (lambda (lat)
    (cond ((null? lat) 0)
          (else (add1 (length (cdr lat)))))))

(define pick
  (lambda (n lat)
    (cond ((= n 1) (car lat))  ;; book -- ((zero? (sub1 n)) (car lat))
          (else (pick (sub1 n) (cdr lat))))))

;; (pick 4 '(lasagna spaghetti ravioli macaroni meatball))

(define rempick
  (lambda (n lat)
    (cond ((zero? (sub1 n)) (cdr lat))
          (else (cons (car lat)
                      (rempick (sub1 n) (cdr lat)))))))

;; (rempick 3 '(hotdogs with hot mustard))

(define no-nums
  (lambda (lat)
    (cond ((null? lat) '())
          ((number? (car lat)) (no-nums (cdr lat)))
          (else (cons (car lat)
                      (no-nums (cdr lat)))))))

;;(no-nums '(5 pears 6 prunes 9 dates))

(define all-nums
  (lambda (lat)
    (cond ((null? lat) '())
          ((number? (car lat)) (cons (car lat)
                                     (all-nums (cdr lat))))
          (else (all-nums (cdr lat))))))

;; (all-nums '(5 pears 6 prunes 9 dates))

(define eqan?
  (lambda (a1 a2)
    (cond ((and (number? a1) (number? a2)) (= a1 a2))
          ((or (number? a1) (number? a2)) #f)
          (else (eq? a1 a2)))))

(define occur
  (lambda (a lat)
    (cond ((null? lat) 0)
          ((eqan? a (car lat)) (add1 (occur a (cdr lat))))
          (else (occur a (cdr lat))))))

;; (occur 3 '(a b c d a b a 3))

(define one?
  (lambda (n)
    (eqan? n 1)))

;;(one? 1)
;;(one? 'one)

(define rempick2
  (lambda (n lat)
    (cond ((one? n) (cdr lat))
          (else (cons (car lat)
                      (rempick2 (cdr lat)))))))

(rempick 3 '(lemon meringue pie))

         
