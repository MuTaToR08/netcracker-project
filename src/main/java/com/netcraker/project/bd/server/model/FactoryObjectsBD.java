package com.netcraker.project.bd.server.model;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.server.model.client.*;
import com.netcraker.project.bd.server.model.location.*;
import com.netcraker.project.bd.server.model.networked.ModelCarrier;
import com.netcraker.project.bd.server.model.networked.ModelPort;
import com.netcraker.project.bd.server.model.networked.ModelRouter;
import com.netcraker.project.bd.server.model.networked.ModelSwitch;
import com.netcraker.project.bd.shared.containers.SimpleContainer;
import com.netcraker.project.bd.shared.objects.ObjectBD;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FactoryObjectsBD {


    private ServletContext context;

    public FactoryObjectsBD(ServletContext context) {
        this.context = context;
    }

    public List<? super ObjectBD> getChields(int id) {
        List<? super ObjectBD> ret = new ArrayList<>();

        Connection cn = ListenerContext.getDBOracle(context);
        Statement st;
        try {
            st = cn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM objects WHERE container_id " + (id==0?"is null":" = "+id) + "");

            while (rs.next())
            {
                SimpleContainer<? super ObjectBD> test = getObject(rs.getInt("object_id"),rs.getInt("object_type_id"));

                if(test.getData() == null)
                    continue;
                ret.add(test.getData());
            }
        } catch(SQLException e){
            e.printStackTrace();
            //catch e;
        }

        return ret;
    }



    public SimpleContainer<? super ObjectBD> getObject(int objectId){
        Connection cn = ListenerContext.getDBOracle(context);
        Statement st;
        SimpleContainer<? super ObjectBD> ret = null;
        try {
            st = cn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM objects WHERE object_id = " + objectId + "");

            rs.next();
            int objectType = rs.getInt("object_type_id");

            ret = getObject(objectId,objectType);

        } catch(SQLException e){
           e.printStackTrace();
        }

        return ret;
    }



    public SimpleContainer<? super ObjectBD> getObject(int objectId, int type)
    {
        SimpleContainer<? super ObjectBD> ret = new SimpleContainer<>();
        switch (type)
        {
            case 1://Здание
                ret.setData(new ModelBuilding(context).getById(objectId));
                break;
            case 2://Этаж
                ret.setData(new ModelFloor(context).getById(objectId));
                break;
            case 3://Комната
                ret.setData(new ModelRoom(context).getById(objectId));
                break;
            case 4://Стойка
                ret.setData(new ModelStand(context).getById(objectId));
                break;
            ////case 5://Оборудование
            ////case 6://Железо
            //case 7://Материнская плата
            //case 8://ОЗУ
            //case 9://Процессор
            //case 10://Видеокарта
            //case 11://Блок питания
            //case 12://Жёсткий диск
            //case 13://SSD
            //case 14://ПК
            case 15://Провод Carrier
                ret.setData(new ModelCarrier(context).getById(objectId));
                break;
            case 16://Комутатор
                ret.setData(new ModelSwitch(context).getById(objectId));
                break;
            case 17://Маршрутизатор
                ret.setData(new ModelRouter(context).getById(objectId));
                break;
            ////case 18://Устройство хранения
            ////case 19://Локация
            //case 20://Сетевая карта
            ////case 21://Сетевое оборудование
            //case 24://Сервер
            //case 25://Неуправляемый комутатор
            //case 26://Управляемый комутатор
            //case 27://настраиваемый комутатор
            case 28://Порт
                ret.setData(new ModelPort(context).getById(objectId));
                break;
            case 29://Заказ
                ret.setData(new ModelOrder(context).getById(objectId));
            case 30://Заказчик
                ret.setData(new ModelCustomer(context).getById(objectId));
                break;
            case 31://Услуга(сервис)
                ret.setData(new ModelService(context).getById(objectId));
                break;
            case 32://Тариф
                ret.setData(new ModelTariff(context).getById(objectId));
                break;
            case 33://Оказание услуги(SCI)
                ret.setData(new ModelCSI(context).getById(objectId));
                break;
            case 34://Операции по счёту(billing)
                ret.setData(new ModelBilling(context).getById(objectId));
                break;
            case 35://Цена (TSP)
                ret.setData(new ModelTSP(context).getById(objectId));
                break;
            case 39://Хранилище
                ret.setData(new ModelStock(context).getById(objectId));
                break;
            case 40://Доп хранилища(для тестироваия)
                ////case 42://Соеденительное
        }

        return ret;
    }
}
