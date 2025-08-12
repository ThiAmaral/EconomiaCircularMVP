package br.ufes.economiacircularmvp.observer.usuario;

public interface IUsuarioSubject {
    void addUsuarioObserver(IUsuarioObserver observer);
    void removeUsuarioObserver(IUsuarioObserver observer);
    void notifyUsuarioObservers();
}
