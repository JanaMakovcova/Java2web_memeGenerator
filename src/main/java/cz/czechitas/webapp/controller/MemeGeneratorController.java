package cz.czechitas.webapp.controller;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class MemeGeneratorController {
    private static final List<String> VYROKY = Arrays.asList(
            "Pravděpodobnost přežití je nepřímo úměrná úhlu, ve kterém přistáváš. Velký úhel, malá pravděpodobnost, a naopak.",
            "Radost z létání je tak živelná a potěšení tak obrovské, že je mezi ostatními sporty nelze opomíjet.",
            "Člověk se snad ani nemůže dívat do dálek a nepřát si přitom, aby měl křídla jako pták.",
            "Protože létám, nezávidím nikomu na světě.",
            "Jsi-li na pochybách, udržuj letovou výšku. S oblohou se ještě nikdy nikdo nesrazil.",
            "Síla turbulence je přímo úměrná teplotě vaší kávy.",
            "Existují tři pravidla pro opravdu hladké přistání. Bohužel nikdo neví, která to vlastně jsou.",
            "Ať už v letadle děláš cokoli, nech ho letět!",
            "Jednou se dá přistát všude.",
            "Vždycky je lepší být na zemi a přát si být ve vzduchu, než být ve vzduchu a přát si být na zemi."
    );
    private static final List<String> AUTORI = Arrays.asList(
            "anonymní letecké moudro",
            "Orville Wright",
            "Richard Burton",
            "Grover C. Norwood",
            "anonymní letecké moudro",
            "druhý Gunterův zákon letecké dopravy",
            "anonymní letecké moudro",
            "anonymní letecké moudro",
            "anonymní letecké moudro",
            "anonymní letecké moudro"
          
    );
    private static final List<String> OBRAZKY = Arrays.asList(
            "https://www.flickr.com/photos/23755697@N04/31752679512/in/photolist-QnSMnA-fSxysB-fSxSJp-8qDP7G-joCZv6-4ZtF71-ku6Fv-ytkgrr-niq3pW-J6YnNJ-oB2G3z-qggKxd-34r6df-hXQozP-2EJBCJ-w9qeav-cw8CUQ-5YGqXA-8AXPQH-FqvqXH-49UCG5-i6dwvL-fdQQ6K-UvRFLv-7AfKH6-nPqW6J-6XWLpy-onxCJG-ccLyPm-y8amSK-rgaxET-HnPtE3-nyZCn2-bj5qE8-JzCvLx-aUXE7K-f2rPBu-GGDdSb-qfTddd-badf8P-BmZRPu-b6uqon-twdUAq-9vg7Zo-VfTicG-GtmMKe-hKbuTD-95srZU-LTNXt9-bsy7wt",
            "https://www.flickr.com/photos/44400809@N07/5581982720/in/photolist-QnSMnA-fSxysB-fSxSJp-8qDP7G-joCZv6-4ZtF71-ku6Fv-ytkgrr-niq3pW-J6YnNJ-oB2G3z-qggKxd-34r6df-hXQozP-2EJBCJ-w9qeav-cw8CUQ-5YGqXA-8AXPQH-FqvqXH-49UCG5-i6dwvL-fdQQ6K-UvRFLv-7AfKH6-nPqW6J-6XWLpy-onxCJG-ccLyPm-y8amSK-rgaxET-HnPtE3-nyZCn2-bj5qE8-JzCvLx-aUXE7K-f2rPBu-GGDdSb-qfTddd-badf8P-BmZRPu-b6uqon-twdUAq-9vg7Zo-VfTicG-GtmMKe-hKbuTD-95srZU-LTNXt9-bsy7wt/",
            "https://www.flickr.com/photos/digitalmelon/3169929370/in/photolist-5Q7HyE-cPwA7d-paQKEd-8tJhFu-9Xv2Rb-7MmJap-6TySWN-euLyLa-c6Ntwb-c6NtBQ-cG79Cy-RWCa7F-5idSAx-adcTbX-4MBrCW-6o4Lsr-d8NzJs-8sAmW7-96a8VX-6C7CPX-23xk2qX-9EKEDC-a9mvmB-aikagF-fADYbB-c6Pn5A-2DF4c2-bfJ5Ji-9BAEo8-4ww9T3-WaxaXt-aygLNh-o7kyGb-9wgM29-WawYCe-W77CQq-8nmwL8-c6PmhQ-WawuRc-6Ktpt7-oAfq6p-6gF5dj-4EShze-VLQYbJ-7yXMxV-bsnLk-79wctw-aLWDSc-5hSmXH-8UhDT8",
            "https://www.flickr.com/photos/13523064@N03/10000315784/in/photolist-geGetE-rNpENr-91hxU3-6TyQ4U-oq7bPB-c6Ntpy-akLQgd-fBqaGn-5uDvkj-7ougqA-bDvjYb-6usGKp-JHeHr-4VRM8q-c6NtiJ-c6PgGC-8UkJXU-a7BUkE-5UWfV6-dLVJJw-fBC12q-d5MVF1-69sdnz-c6PhyW-5Q7HyE-cPwA7d-paQKEd-8tJhFu-9Xv2Rb-7MmJap-6TySWN-euLyLa-c6Ntwb-c6NtBQ-cG79Cy-RWCa7F-5idSAx-adcTbX-4MBrCW-6o4Lsr-d8NzJs-8sAmW7-96a8VX-6C7CPX-23xk2qX-9EKEDC-a9mvmB-aikagF-fADYbB-c6Pn5A",
            "https://www.flickr.com/photos/houdinifx/3865330418/in/photolist-6TyQ4U-oq7bPB-c6Ntpy-akLQgd-fBqaGn-5uDvkj-7ougqA-bDvjYb-6usGKp-JHeHr-4VRM8q-c6NtiJ-c6PgGC-8UkJXU-a7BUkE-5UWfV6-dLVJJw-fBC12q-d5MVF1-69sdnz-c6PhyW-5Q7HyE-cPwA7d-paQKEd-8tJhFu-9Xv2Rb-7MmJap-6TySWN-euLyLa-c6Ntwb-c6NtBQ-cG79Cy-RWCa7F-5idSAx-adcTbX-4MBrCW-6o4Lsr-d8NzJs-8sAmW7-96a8VX-6C7CPX-23xk2qX-9EKEDC-a9mvmB-aikagF-fADYbB-c6Pn5A-2DF4c2-bfJ5Ji-9BAEo8",
            "https://www.flickr.com/photos/telstar/4895430498/in/photolist-8sAmW7-96a8VX-6C7CPX-23xk2qX-9EKEDC-a9mvmB-aikagF-fADYbB-c6Pn5A-2DF4c2-bfJ5Ji-9BAEo8-4ww9T3-WaxaXt-aygLNh-o7kyGb-9wgM29-WawYCe-W77CQq-8nmwL8-c6PmhQ-WawuRc-6Ktpt7-oAfq6p-6gF5dj-4EShze-VLQYbJ-7yXMxV-bsnLk-79wctw-aLWDSc-5hSmXH-8UhDT8-dbFfk-oqzzhi-ngESKk-cPwCV5-7bPZAi-FKZJ2m-V8xcNK-szS33T-6NQ6PN-oLrnqp-a8XpZz-vx3ru8-zXd1PA-hSqJcL-d9XwsU-aP3jQt-3Leqmm",
            "https://www.flickr.com/photos/jdominiq/8664627257/in/photolist-ecEtP8-6MDRqW-5Y6YcP-76NUxV-8DgqZs-dAqSDt-8GaTa9-37fXsz-2r9VXT-csnJWs-bmagpy-3asLwH-2G2N7Q-dATp1p-7roogH-rhAHLw-djcZgs-dTcmUM-7zN98T-dJdwQ7-9YfgCx-diyzNM-dhxCQ6-9aUoyP-8NULXB-FiGi2u-dTci84-vpuGYG-dgT65E-pvYdLV-FiG8kL-5s8CoL-adW4xJ-YBLfSf-dhDyc2-dHs7HW-s1r9V6-97LdXj-qrwoQj-9EQVee-225eiSB-2ymhKP-dtEZmm-qKABB3-7zMUrT-3bouEQ-qa9Ux2-dhxr37-57t2bW-8to7Bn",
            "https://www.flickr.com/photos/aero_icarus/8455327913/in/photolist-dTaLpK-8aG9Sk-dJ8KzX-aEu6Hi-9aXkwL-97GBBk-9aXz59-fKm35p-97L6PJ-dtMx8w-wSuT45-8SErt-8trazE-93fgzc-9sF4kT-91zai3-dDaFEt-wSvcUS-9Yh5iK-9sHF9S-ardEf8-9vLLPz-9aUrdF-8EZ5Mn-e1uMNt-drSNRp-22mSnrQ-amo2Pz-8P6wvS-7vnhGp-dtG1qB-9aUbSX-9aXki3-8HHFFX-9sHCBU-4onkva-8DjsU5-9EQRDr-dJ8fEH-dJedgC-wSvnib-dAToAK-dATsGa-wd79Py-8NV2w8-8GU4kQ-9sJ2kU-8QxZSF-9CYkpc-9aUkwg",
            "https://www.flickr.com/photos/topten/2545457201/in/photolist-4SW8K8-VLR17N-7ouhbd-GrHHvU-bBtNrA-b3P97X-c6PoYJ-gW5pZV-avca1T-LLtFtz-nL32Ni-XdzG6o-21K3jXq-oSGygu-M7pZY-LTcEWd-3CmaeS-6YiuCK-FgrCht-FfEckC-54LX1r-y2BeHU-LyXTCG-siS7Ee-Kpeznu-s1ra6M-H14PZA-5ifteH-88sD6D-6Dx9xN-cXFrZW-c6NqBh-vCfuV4-gGsxCb-sVZECf-4a9YcX-GFvvyx-6B1xhw-VLS5ws-W78tm3-8mUCrW-gxEvYE-VsBGjP-hC5XNq-mpD3Pa-Cai66P-FDoBKu-SeW856-rUR7uv-jjw3rg",
            "https://www.flickr.com/photos/69353231@N02/7850028556/in/photolist-cXFrZW-c6NqBh-vCfuV4-rRGE6v-8YXyvk-BVqKWf-59SBCL-D5nrzw-ei1iWe-D1AK9h-nbkyf3-ZBcayW-agBBWS-yWHffe-VLdTn3-4Xy3gk-4dUCht-4mvTdW-9j3FXA-59nEM4-WawyDn-jSKeQi-fNc6He-A2QRr2-3bogRa-b3P7xn-auBAxg-VLR1XW-4yiPMx-8A1JPD-qoUemA-gqqAaK-xvQicc-LgmC5f-JTC2NQ-9KJskU-iqLAnA-5ijL9j-eV5nN-7wePhy-7oqmdx-yruwnx-AF6qQW-2LQxxC-2322ZYT-D9h5ge-XGZGtJ-55fnm3-e5wTjg-geGaJz"

            );
    
    @RequestMapping("/meme.html")
       public ModelAndView zobrazMeme() {
        ModelAndView obrazekAVyrok = new ModelAndView("meme-template");
        Random generatorNahodnychCisel = new Random();
        int nahodneCisloObrazek = generatorNahodnychCisel.nextInt(10) + 1 ;
        int nahodneCisloVyrok = generatorNahodnychCisel.nextInt(10);
        obrazekAVyrok.addObject("obrazekNahodny", nahodneCisloObrazek);
        obrazekAVyrok.addObject("vyrokNahodny", VYROKY.get(nahodneCisloVyrok));
        obrazekAVyrok.addObject("vyrokAutor", AUTORI.get(nahodneCisloVyrok));
        obrazekAVyrok.addObject("linkZdroj",OBRAZKY.get(nahodneCisloObrazek - 1));
        return obrazekAVyrok;
    }
}
