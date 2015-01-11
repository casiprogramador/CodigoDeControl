/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigocontrol;

/**
 *
 * @author marcelo
 */
public class CodigoControl {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
        
        //Primera parte
        String numAu = "8004005263848";
        String numFac = "673173";
        String nit = "1666188";
        String fechaTrans = "20080810";
        String montoTrans = "51330";
        String llave = "PNRU4cgz7if)[tr#J69j=yCS57i=uVZ$n@nv6wxaRFP+AUf*L7Adiq3TT[Hw-@wt";
        Verhoeff verhoeff = new Verhoeff();
        
        numFac = numFac+verhoeff.generate(numFac);
        numFac = numFac+verhoeff.generate(numFac);
        
        nit = nit+verhoeff.generate(nit);
        nit = nit+verhoeff.generate(nit);
        
        fechaTrans = fechaTrans+verhoeff.generate(fechaTrans);
        fechaTrans = fechaTrans+verhoeff.generate(fechaTrans);
        
        montoTrans = montoTrans+verhoeff.generate(montoTrans);
        montoTrans = montoTrans+verhoeff.generate(montoTrans);
        
        Long suma = Integer.parseInt(numFac)+Long.parseLong(nit)
                +Integer.parseInt(fechaTrans)+Integer.parseInt(montoTrans);
        
        String digVan = Long.toString(suma);
        digVan = digVan+verhoeff.generate(digVan);
        digVan = digVan+verhoeff.generate(digVan);
        digVan = digVan+verhoeff.generate(digVan);
        digVan = digVan+verhoeff.generate(digVan);
        digVan = digVan+verhoeff.generate(digVan);
        
        //Fin Primera parte
        
        //Segunda Parte
        digVan = digVan.substring(digVan.length()-5);
        //String[] arrDigVan = digVan.split("");
        char[] arrDigVan = digVan.toCharArray();
        int dig1 = Character.getNumericValue(arrDigVan[0])+1;
        int dig2 = Character.getNumericValue(arrDigVan[1])+1;
        int dig3 = Character.getNumericValue(arrDigVan[2])+1;
        int dig4 = Character.getNumericValue(arrDigVan[3])+1;
        int dig5 = Character.getNumericValue(arrDigVan[4])+1;
        
        String str1 = llave.substring(0,dig1);
        String str2 = llave.substring(dig1,dig1+dig2);
        String str3 = llave.substring(dig1+dig2,dig1+dig2+dig3);
        String str4 = llave.substring(dig1+dig2+dig3,dig1+dig2+dig3+dig4);
        String str5 = llave.substring(dig1+dig2+dig3+dig4,dig1+dig2+dig3+dig4+dig5);
        numAu = numAu+str1;
        numFac = numFac+str2;
        nit = nit+str3;
        fechaTrans = fechaTrans+str4;
        montoTrans = montoTrans+str5;
        
        
        //Fin parte 2
        //Parte 3
        AllegedRC4 alleged = new AllegedRC4();
        String digAlleged = alleged.generar(numAu+numFac+nit+fechaTrans+montoTrans, llave+digVan);
        int sum = 0;
        //Fin Parte 3
        //Parte 4
            
        
        char[] dig = digAlleged.toCharArray();
        for (int i = 0; i < dig.length; i++) {
            sum = sum + (int)dig[i];
        }
        
        int sp1 = sumaAscii(digAlleged,0);
        int sp2 = sumaAscii(digAlleged,1);
        int sp3 = sumaAscii(digAlleged,2);
        int sp4 = sumaAscii(digAlleged,3);
        int sp5 = sumaAscii(digAlleged,4);
        
        //Fin Parte 4
        //Parte 5
        sp1 = sp1 * sum / dig1;
        sp2 = sp2 * sum / dig2;
        sp3 = sp3 * sum / dig3;
        sp4 = sp4 * sum / dig4;
        sp5 = sp5 * sum / dig5;
        
        int sumSp = sp1+sp2+sp3+sp4+sp5;
        Base64 base64 = new Base64();
        String codigoControl = alleged.generar(base64.generate(sumSp), llave+digVan);
        System.out.println(codigoControl);

        
        
          
    }
    
    static public int sumaAscii(String valor,int inicio){
        int suma = 0;
        char[] digitoValor = valor.toCharArray();
        while(inicio < digitoValor.length){
            suma = suma + (int)digitoValor[inicio];
            inicio = inicio + 5;            
        }       
        return suma;
    }
}
