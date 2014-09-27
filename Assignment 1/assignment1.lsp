;*********************Assignment 1: Lisp Programming *******************
; ***** Neal Friedman **************************************************
;****** CS 3210 ********************************************************
 
 
;******* number 1 ************  works ***
(defun deleteAt( L int )
	(cond ((null L) nil)
		  ((= int 0) (cdr L))
			(T (cons (car L) (deleteAt (cdr L) (- int 1))))
	)
)

;******* number 2 *************   works ****
(defun deleteAllAt(L &rest R)
	(cond ((null L) L)
		(T (cond ((null R) L)
				(T (deleteAllatHelper L R)))
		)			
	)
)
(defun deleteAllAtHelper(L R)
	(cond ((null L) L)
		(T (cond ((null R) L)
			(T  (cond ( (= (car R) 0) (deleteAllAtHelper (cdr L) (decrement (cdr R) ) ) )
				(T (cons (car L) (deleteAllAtHelper  (cdr L) (decrement R) ) ) )))
		   )
		)
	)
)

(defun decrement (R)
	(cond ((null R) R)
		(T (cons (- (car R) 1) (decrement (cdr R))))
	)
)

;*********** number 3 ************** works ****

(defun sumAll (&rest R)
	(sumAllFixer R)
)
	
(defun sumAllFixer (R)
	(cond ((null R) 0)
		(T (cond ((listp (car R))
					(sumAllFixer (cons (eval (car R)) (cdr R))))
				(T (+ (car R) (sumAllFixer (cdr R))))
		   )
		)
	)
)

;********* number 4 *************   works *****

(defun similar (A B)
	(cond ((null A) nil)														
		(T (cond ((null B) nil)												
				 ((eq (car A) (car B)) (cons (car A) (similar (cdr A) B)))
				 (T (append (similarHelper A (cdr B)) (similar (cdr A) B))))
		)
   	)			 
) 
   				   
(defun similarHelper (A B)  
   	 (cond ((null A) nil) 													
   		   (T (cond ((null B) nil)											
   					 ((eq (car A) (car B)) (list (car A))) 					
   					 (T (similarHelper A (cdr B)))) 						
   				)
   	)
)

;********* number 5 ************  works ****

(defun different (lst1 lst2)
	(cond ((null lst1) (print "Argument error: List 1 is empty"))
		(T (cond ((null lst2) (print "Argument error: List 2 is empty"))
				(T (append (differentList lst1 lst2) (differentList lst2 lst1))))	
		)
	)
)

(defun differentList  (lst1 lst2)
	(cond ((null lst1) Nil)
		(T (cond ((member (car lst1) lst2) (differentList (cdr lst1) lst2))
			(T (cons (car lst1) (differentList (cdr lst1) lst2))))
		)
	)
)

;************ number 6 ***** works

(defun showLast(L)
	(cond((null L) nil)
		(T (cond((null (cdr L)) (car L))
			(T (showLast(cdr L ))))
		)
	)
)

;************ number 7 ************ works *****

(defun mcons(&rest R)
	(cond ( (null R) (print "No Arguments") )
		(T (mconsHelper R) )
	)
)

(defun mconsHelper (R)
	(cond ((null (cdr R) ) (car R))
		(T (append  (list(car R)) (mconsHelper (cdr R))))
	)
)

;************ number 8 ****************  works ****

(defun nthList (list int) ;; currently only shows the sublist at the provided index
	(cond ((null list) nil)
		(T (cons (nthSubList (car list) int) (nthList (cdr list) int)))	
	)
)

(defun nthSubList (list int)
	(cond ((null list) nil)
		(T (cond ((= 0 int) (car list))
			(T (nthSubList (cdr list) (- int 1))))
		)
	)
)
