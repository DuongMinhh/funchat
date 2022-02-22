import './App.css';
import { Route, Routes } from 'react-router-dom'
import Login from './components/auth/Login';
import Main from './components/app/Main';
import Outh2Response from './components/auth/Oauth2Response';
import AuthProvider from './context/AuthProvider';
import AppProvider from './context/AppProvider';
import AddRoom from './components/app/modals/AddRoom';
import AddMember from './components/app/modals/AddMember';
import { UseInfoModal } from './components/app/modals/UseInfoModal';

function App() {
  return (
    <AuthProvider>
      <AppProvider>
        <Routes>
          <Route path='/' element={<Main />} />
          <Route path='/login' element={<Login />} />
          {/* <Route path='/oauth2/redirect' element={<Outh2Response />} /> */}
        </Routes>
        <AddRoom/>
        <AddMember/>
        <UseInfoModal/>
      </AppProvider>
    </AuthProvider>
  );
}

export default App;
