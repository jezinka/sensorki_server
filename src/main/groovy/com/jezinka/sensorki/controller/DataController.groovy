package com.jezinka.sensorki.controller

import org.json.simple.JSONObject
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.ThreadLocalRandom

@RestController
class DataController {

    @RequestMapping(value = "/data.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    JSONObject home() {
        def data = ["hum_temp": "Temperatura (\u00b0C)", "hum_hum": "Wilgotno\u015b\u0107 (%)", "bar_pres_rel": "Ci\u015bnienie (hPa)", "lux": "Jasno\u015b\u0107 (lx)", "vbat": "Nap. baterii (V)", "vreg": "Nap. sensor\u00f3w (V)"]
        def sensors = [:]
        def readings = [:]

        ['Balkon', 'Salon', 'Biuro', 'Łazienka', 'Sypialnia', 'Piwnica'].eachWithIndex { String room, int idx ->
            sensors << [(idx): ["label": room, "data": ["hum_temp", "hum_hum", "lux", "vbat", "vreg"]]]
            readings << [(idx): ["id": randomId, "stamp": timeStamp, "sensor_id": 1, "seq": randomId, "status": 0, "flags": randomId, "bar_temp": randomTemp, "bar_pres_abs": 1002.67, "bar_pres_rel": 1018.63, "lux": randomLux, "hum_temp": randomTemp, "hum_hum": randomHumHum, "vbat": randomVBat, "vreg": randomVReg]]
        }

        ["sensors": sensors, "readings": readings, "data": data]
    }

    private static int getRandomId() {
        ThreadLocalRandom.current().nextInt()
    }

    private BigDecimal getTimeStamp() {
        //tylko sekundy w tył
        new Date().getTime() / 1000
    }

    static Double getRandomTemp() {
        ThreadLocalRandom.current().nextDouble(-40, 40)
    }

    static Double getRandomVBat() {
        ThreadLocalRandom.current().nextDouble(0, 4.2)
    }

    static Double getRandomVReg() {
        ThreadLocalRandom.current().nextDouble(1.8, 3.3)
    }

    static Double getRandomHumHum() {
        ThreadLocalRandom.current().nextDouble(0, 100)
    }

    static Double getRandomLux() {
        ThreadLocalRandom.current().nextDouble(0, 5000)
    }
}