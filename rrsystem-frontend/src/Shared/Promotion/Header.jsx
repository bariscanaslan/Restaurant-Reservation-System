import { Link } from 'react-router-dom';

function Header() {
    return (
        <header className='header-custom'>
            <h1>Restaurant Reservation System</h1>
            <nav>
                <ul>
                    <li><Link to="/">Home</Link></li>
                    <li><Link to="/pricing">Pricing</Link></li>
                    <li><Link to="/features">Features</Link></li>
                    <li><Link to="/contact">Contact</Link></li>
                </ul>
            </nav>
            <hr></hr>
        </header>
    );
}

export default Header