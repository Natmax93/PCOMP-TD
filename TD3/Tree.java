import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

abstract class Tree<T>{
    abstract int size();
    abstract int height();
    abstract boolean mem(T elem);
    abstract List<T> toList();
}

class EmptyTree<T> extends Tree<T>{

    @Override
    int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    int height() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    boolean mem(T elem) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    List<T> toList() {
        return new ArrayList<>();
    }
}

class BinaryNode<T> extends Tree<T>{

    T elem;
    Tree<T> fg, fd;

    @Override
    int size() {
        return fg.size() + fd.size() + 1;
    }

    @Override
    int height() {
        return 1 + Math.max(fg.height(), fd.height());
    }

    @Override
    boolean mem(T elem) {
        if (this.elem == elem)
            return true;
        return fg.mem(elem) || fd.mem(elem);
    }

    @Override
    List<T> toList() {
        List<T> liste = new ArrayList<>();
        liste.add(elem);
        liste.addAll(fg.toList());
        liste.addAll(fd.toList());
        return liste;
    }  
}
