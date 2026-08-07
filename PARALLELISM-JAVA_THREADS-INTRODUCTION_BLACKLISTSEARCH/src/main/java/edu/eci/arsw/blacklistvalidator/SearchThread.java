package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.List;

/**
 * Hilo encargado de revisar un segmento (rango) del conjunto total de
 * servidores de listas negras, buscando ocurrencias de una IP dada.
 * Se detiene apenas ese segmento acumula BLACK_LIST_ALARM_COUNT ocurrencias.
 */
public class SearchThread extends Thread {

    private int startIndex;
    private int endingIndex;
    private String ipAddress;
    private int ocurrencesCount;
    private int checkedListsCountThread;
    private HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();
    private LinkedList<Integer> blackListOcurrences = new LinkedList<>();

    public SearchThread(int startIndex, int endIndex, String ipAddress) {
        this.startIndex = startIndex;
        this.endingIndex = endIndex;
        this.ipAddress = ipAddress;
        this.ocurrencesCount = 0;
        this.checkedListsCountThread = 0;
    }

    @Override
    public void run() {
        for (int i = startIndex; i <= endingIndex && ocurrencesCount < HostBlackListsValidator.BLACK_LIST_ALARM_COUNT; i++) {
            checkedListsCountThread++;
            if (skds.isInBlackListServer(i, ipAddress)) {
                blackListOcurrences.add(i);
                ocurrencesCount++;
            }
        }
    }

    public int getCheckedListsCountThread() {
        return checkedListsCountThread;
    }

    public int getOcurrencesCount() {
        return ocurrencesCount;
    }

    public LinkedList<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }
}