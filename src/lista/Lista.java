/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

import java.util.Iterator;

/**
 *
 * @author Guest
 */
public class Lista<Item extends Comparable<? super Item>> implements Iterable<Item> {

    private Node<Item> first;

    private class Node<Item> {

        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }

    public Lista() {
        first = null;
    }

    public boolean search(Item item) {
        if (isEmpty()) {
            return false;
        } else {
            Node<Item> tmp = first;
            while (tmp != null) {
                if (tmp.item instanceof Persona) {
                    Persona personaTmp = (Persona) tmp.item;
                    if (personaTmp.getNombre().equals(((Persona) item).getNombre()) && personaTmp.getEdad() == ((Persona) item).getEdad()) {
                        return true;
                    }
                }
                tmp = tmp.next;
            }
            return false;
        }

    }

    public Item maxItem() {
        if (isEmpty()) {
            return null;
        }
        Node<Item> tmp = first;
        Item candidato = tmp.item;
        while (tmp != null) {
            if (candidato.compareTo(tmp.item) < 0) {
                candidato = tmp.item;
            }
            tmp = tmp.next;
        }
        return candidato;
    }

    public void addFirst(Item item) {
        Node<Item> nuevo = new Node<Item>(item);
        nuevo.next = first;
        first = nuevo;
    }

    public void addSorted(Item item) {
        int indexToInsert = size() / 2; // Calcula el índice intermedio
        Node<Item> current = first;
        int currentIndex = 0;

        while (current.next != null && currentIndex < indexToInsert - 1) {
            current = current.next;
            currentIndex++;
        }

        // Crea un nuevo nodo para el elemento
        Node<Item> newNode = new Node<>(item);

        if (currentIndex == 0) {
            // Inserta el nuevo nodo antes del primer elemento
            newNode.next = current.next;
            current.next = newNode;
        } else {
            // Inserta el nuevo nodo después del elemento actual
            newNode.next = current.next;
            current.next = newNode;
        }

    }

    public void addLast(Item item) {
        Node<Item> nuevo = new Node<Item>(item);
        if (isEmpty()) {
            first = nuevo;
        } else {
            Node<Item> tmp = first;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = nuevo;
        }
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        } else if (first.next == null) {
            Item item = first.item;
            first = null;
            return item;
        } else {
            Node<Item> tmp = first;
            Node<Item> before = null;
            while (tmp.next != null) {
                before = tmp;
                tmp = tmp.next;
            }
            before.next = null;
            return tmp.item;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        Node<Item> tmp = first;
        int i = 0;
        while (tmp != null) {
            tmp = tmp.next;
            i++;
        }
        return i;
    }

    public void showList() {
        Node<Item> tmp = first;
        while (tmp != null) {
            System.out.println(tmp.item);
            tmp = tmp.next;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                return null;
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
