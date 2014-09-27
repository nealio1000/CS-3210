;**** Neal Friedman ****************************************************
;**** Principles of Programming Languages ******************************
;**** Lisp Project *****************************************************
;**** Dr. Aaron Gordon *************************************************

; *********** Task List ************************************************

(setq tasks '((purchase_lot 2)
			  (design_house 5)
			  (get_permit 1 purchase_lot design_house)
			  (get_bids 14 purchase_lot design_house) 
			  (select_subs 2 get_bids)
			  (excavate 1 get_permit select_subs) 
			  (construct_basement 7 excavate)
			  (order_windows_doors 3 purchase_lot design_house) 
			  (get_windows_doors 10 order_windows_doors)
			  (frame 12 get_permit select_subs) 
			  (rough_plumbing 5 frame)
			  (rough_electric 3 frame)
			  (roof 4 frame)
			  (install_windows_doors 7 get_windows_doors rough_plumbing rough_electric)
			  (vapor_barrier_insulation 2 roof install_windows_doors)
			  (drywall 5 vapor_barrier_insulation)
			  (inside_paint 3 drywall)
			  (cupboards 3 inside_paint)
			  (carpet_floor 5 inside_paint)
			  (lights 2 inside_paint)
			  (plumbing_heating 6 inside_paint)
			  (siding 2 roof install_windows_doors)
			  (outside_paint 3 siding)
			  (move_house 1 cupboards carpet_floor lights plumbing_heating outside_paint)
			  (connections 2 construct_basement move_house)
			  (landscape 4 construct_basement move_house)
			  (move_in 0 landscape connections)
			 )
)

;**** Functions ********************************************************

; ****** One ********** works ****


(defun sum (list)
	(cond ((null (cdr list)) 0)
		(T (+ (cadar list) (sum (cdr list))))
	)
)

; ***** two ********* works *****
		
(defun predecessors (job list)
	(cond ((null list) nil)
		((equal job (caar list) ) (cddar list)) 
		(T (predecessors job (cdr list)))
	)
)

; ***** Three ********** works ******

(defun getTime (job list)
	(cond ((null list) nil)
		  ((equal job (caar list)) (cadar list)) 
		  (T (getTime job (cdr list)))
	)
)

;***** Four ************* works ****

(defun get_all_preds (job list)
	(remove-duplicates (append (predecessors job list)  
			(get_all_preds_helper (predecessors job list) list)):from-end t)
)

(defun get_all_preds_helper (predlist list) 
	(cond ((null predlist) nil)
		(T (append (get_all_preds (car predlist) list)
			       (get_all_preds_helper (cdr predlist) list))
		)
	)
)
			 
;***** Five **************** works ******

(defun precedes (job1 job2 list)
	(cond ((member job1 (get_all_preds job2 list) ) T)
		(T nil)
	)
)	

;***** Six ****************** works *********

(defun start_day (job list)
  (sumPredDays (predecessors job list) list))

(defun sumPredDays (predlist list)
    (cond ((null predlist) 1)
          ((null list) nil)
          (T (max (+ (getTime (car predlist) list) (sumPredDays (predecessors (car predlist) list) list)) 	
					(sumPredDays (cdr predlist) list))
					
		  )
    )
)
	
;**** Seven ***************** works but doesn't return the specified result **********

(defun get_max (joblist list)
	(cond ((null joblist) 0)
		(T (max (+ (start_day (car joblist) list) (getTime (car joblist) list)) (get_max (cdr joblist) list)))
	)
)

;**** Eight ********************

(defun critical_path (job list)
	(cond ((null list) nil))
)

;**** Nine ********************

(defun depends_on (job list)
	(cond ((null list) nil))
)
