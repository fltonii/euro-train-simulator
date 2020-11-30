package com.company.LinkedList;

/**
 * Interface que define o comportamento de uma lista linear.
 */
public interface List<E> {
    /**
     * Informa a quantidade de elementos armazenados na lista.
     * @return A quantidade de elementos armazenados na lista.
     */
    public int numElements();

    /**
     * Informa se a lista estÃ¡ vazia.
     * @return Verdadeiro se a lista estiver vazia,
     * 			falso caso contrÃ¡rio.
     */
    public boolean isEmpty();

    /**
     * Informa se a lista estÃ¡ cheia.
     * @return Verdadeiro se a lista estiver cheia,
     * 			falso caso contrÃ¡rio.
     */
    public boolean isFull();

    /**
     * Insere um novo elemento na posiÃ§Ã£o indicada.
     * @param element O elemento a ser inserido
     * @param pos A posiÃ§Ã£o onde o elemento serÃ¡ inserido
     * 				(iniciando em 0)
     */
    public void insert(E element, int pos);

    /**
     * Remove o elemento da posiÃ§Ã£o indicada.
     * @param pos A posiÃ§Ã£o de onde o elemento serÃ¡ removido
     * 				(iniciando em 0)
     * @return O elemento removido
     */
    public E remove(int pos);

    /**
     * Retorna o elemento da posiÃ§Ã£o indicada, sem removÃª-lo.
     * @param pos A posiÃ§Ã£o do elemento
     * @return O elemento
     */
    public E get(int pos);

    /**
     * Localiza a primeira ocorrÃªncia do elemento indicado na lista.
     * @param element O elemento a ser localizado
     * @return A posiÃ§Ã£o da primeira ocorrÃªncia do elemento,
     * 			ou -1 se ele nÃ£o for encontrado.
     */
    public int search(E element);
}