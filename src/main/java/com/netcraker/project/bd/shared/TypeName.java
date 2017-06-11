package com.netcraker.project.bd.shared;

/**
 * Created by mutat on 06.06.2017.
 */
public enum TypeName {

    BUILDING, FLOOR,ROOM,STAND,EQUIPMENT,HARDWARE,MOTHERBOARD,MEMORY,CPU,GPU,POWER,
    HDD,SSD,PC,CARRIER,SWITCH,ROUTER,STORAGE,LOCATION,NETWORK_CARD,NETWORKED,SERVER,
    SWITCH_NO_CONTROL,SWITCH_CONTORL,SWITCH_SETTING,PORT,ORDER,CUSTOMER,SERVICE,
    TARIFF,CSI,BILLING,TSP,STOCK,NEW_STAGE,CONNECTING,UNKNOWN,;
    public static TypeName forType(int i)
    {
        switch (i)
        {
            case 1:
                return BUILDING;
            case 2:
                return FLOOR;
            case 3:
                return ROOM;
            case 4:
                return STAND;
            case 5:
                return EQUIPMENT;
            case 6:
                return HARDWARE;
            case 7:
                return MOTHERBOARD;
            case 8:
                return MEMORY;
            case 9:
                return CPU;
            case 10:
                return GPU;
            case 11:
                return POWER;
            case 12:
                return HDD;
            case 13:
                return SSD;
            case 14:
                return PC;
            case 15:
                return CARRIER;
            case 16:
                return SWITCH;
            case 17:
                return ROUTER;
            case 18:
                return STORAGE;
            case 19:
                return LOCATION;
            case 20:
                return NETWORK_CARD;
            case 21:
                return NETWORKED;
            case 22:
                return UNKNOWN;
            case 23:
                return UNKNOWN;
            case 24:
                return SERVER;
            case 25:
                return SWITCH_NO_CONTROL;
            case 26:
                return SWITCH_CONTORL;
            case 27:
                return SWITCH_SETTING;
            case 28:
                return PORT;
            case 29:
                return ORDER;
            case 30:
                return CUSTOMER;
            case 31:
                return SERVICE;
            case 32:
                return TARIFF;
            case 33:
                return CSI;
            case 34:
                return BILLING;
            case 35:
                return TSP;
            case 36:
                return UNKNOWN;
            case 37:
                return UNKNOWN;
            case 38:
                return UNKNOWN;
            case 39:
                return STOCK;
            case 40:
                return NEW_STAGE;
            case 41:
                return CONNECTING;

        }
        return UNKNOWN;
    }
}
