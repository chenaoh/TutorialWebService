package co.quindio.sena.tutorialwebservice.interfaces;


import co.quindio.sena.tutorialwebservice.fragments.BienvenidaFragment;
import co.quindio.sena.tutorialwebservice.fragments.ConsultaListaUsuarioImagenUrlFragment;
import co.quindio.sena.tutorialwebservice.fragments.ConsultaUsuarioUrlFragment;
import co.quindio.sena.tutorialwebservice.fragments.ConsultarListaUsuariosFragment;
import co.quindio.sena.tutorialwebservice.fragments.ConsultarUsuarioFragment;
import co.quindio.sena.tutorialwebservice.fragments.ConsutarListausuarioImagenFragment;
import co.quindio.sena.tutorialwebservice.fragments.DesarrolladorFragment;
import co.quindio.sena.tutorialwebservice.fragments.RegistrarUsuarioFragment;

/**
 * Created by CHENAO on 5/08/2017.
 */

public interface IFragments extends BienvenidaFragment.OnFragmentInteractionListener,DesarrolladorFragment.OnFragmentInteractionListener,
        RegistrarUsuarioFragment.OnFragmentInteractionListener,ConsultarUsuarioFragment.OnFragmentInteractionListener,
        ConsultarListaUsuariosFragment.OnFragmentInteractionListener,ConsutarListausuarioImagenFragment.OnFragmentInteractionListener,
        ConsultaUsuarioUrlFragment.OnFragmentInteractionListener,ConsultaListaUsuarioImagenUrlFragment.OnFragmentInteractionListener{
}
