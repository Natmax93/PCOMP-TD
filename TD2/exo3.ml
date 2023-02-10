type point = {xc:int ; yc:int} ;;
type mobile = {pos:point ; depl:point -> mobile} ;;

let translate (dx:int) (dy:int) (p:point) : point =
  {xc = p.xc + dx ; yc = p.yc + dy} ;;

let rec immobile (pt:point) =
  {pos = pt ; depl = immobile} ;;

let rec uniforme (dx:int) (dy:int) (pt:point) : mobile =
  {pos = translate dx dy pt ;
  depl = uniforme dx dy } ;;

let depl1 = uniforme 1 2 ;;

let p1 = {xc = 10 ; yc = 20} ;;

depl1 p1 ;;
