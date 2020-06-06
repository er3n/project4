/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.api;

/**
 *
 * @author ersin
 */
public final class RestPaths {

    private static final String BASE_PATH = "/rest/v1";

    public static final class HastaRest {

        public static final String Path = BASE_PATH + "/hasta";
    }

    public static final class KurumKurulusRest {

        public static final String Path = BASE_PATH + "/kurumkurulus";
    }

    public static final class MsgHastaMesajRest {

        public static final String Path = BASE_PATH + "/msghastamesaj";
    }

    public static final class MsgZamanliMesajAliciRest {

        public static final String Path = BASE_PATH + "/msgzamanlimesajalici";
    }

    public static final class MsgZamanliMesajKalipRest {

        public static final String Path = BASE_PATH + "/msgzamanlimesajkalip";
    }

    public static final class NitGozlemDegerRest {

        public static final String Path = BASE_PATH + "/nitgozlemdeger";
    }

    public static final class NitHastaCihazRest {

        public static final String Path = BASE_PATH + "/nithastacihaz";
    }

    public static final class NitHastaKomplikasyonRest {

        public static final String Path = BASE_PATH + "/nithastakomplikasyon";
    }

    public static final class NitKompliksyonTedaviRest {

        public static final String Path = BASE_PATH + "/nitkomplikasontedavi";
    }

    public static final class SecProfileRest {

        public static final String Path = BASE_PATH + "/secprofil";
    }

    public static final class SecUserRest {

        public static final String Path = BASE_PATH + "/secuser";
    }

    public static final class TipCihazRest {

        public static final String Path = BASE_PATH + "/tipcihaz";
    }

    public static final class TipGozlemRest {

        public static final String Path = BASE_PATH + "/tipgozlem";
    }

    public static final class TipOlayRest {

        public static final String Path = BASE_PATH + "/tipolay";
    }

    public static final class TipTedaviRest {

        public static final String Path = BASE_PATH + "/tiptedavi";
    }

    public static final class TipTedaviSonucRest {

        public static final String Path = BASE_PATH + "/tiptedavisonuc";
    }

    
    public static final class LogTedisRest {

        public static final String Path = BASE_PATH + "/logtedis";
    }

}
