function Aside() {
    return (
        <nav id="sidebar">
            <div className="img bg-wrap text-center py-4">
                <div className="user-logo">
                    <div className="img" style={{ backgroundImage: 'url(https://bariscanaslan.com/Assets/img/Bme.jpg)' }}></div>
                    <h3>Barış Can Aslan</h3>
                </div>
            </div>
            <ul className="list-unstyled components mb-5">
                <li className="active">
                    <a href="#"><span className="fa fa-home mr-3"></span> Home</a>
                </li>
                <li>
                    <a href="#"><span className="fa fa-download mr-3"></span> User List</a>
                </li>
                <li>
                    <a href="#"><span className="fa fa-gift mr-3"></span> Edit Manager</a>
                </li>
                <li>
                    <a href="#"><span className="fa fa-sign-out mr-3"></span> Edit Prices</a>
                </li>
                <li>
                    <a href="#"><span className="fa fa-trophy mr-3"></span> Restaurants</a>
                </li>
                <li>
                    <a href="#"><span className="fa fa-cog mr-3"></span> Cuisines</a>
                </li>
                <li>
                    <a href="#"><span className="fa fa-support mr-3"></span> Reservations</a>
                </li>
                <li>
                    <a href="#"><span className="fa fa-sign-out mr-3"></span> Locations</a>
                </li>
                <li>
                    <a href="#"><span className="fa fa-sign-out mr-3"></span> Revenue</a>
                </li>
                <li>
                    <a href="#"><span className="fa fa-sign-out mr-3"></span> Sign Out</a>
                </li>

            </ul>
        </nav>
    );
}

export default Aside;