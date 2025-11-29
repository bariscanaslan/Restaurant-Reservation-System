import React, { useEffect } from 'react';
import Aside from "../../../Shared/Dashboard/Admin/Aside.jsx";

const Admin = () => {
    useEffect(() => {

        const styles = [
            "https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css",
            "https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900",
            "../../../Libs/css/sidebar/font-awesome.min.css",
            "../../../Libs/css/sidebar/aside-menu.css"
        ];

        const styleElements = styles.map(href => {
            const link = document.createElement("link");
            link.rel = "stylesheet";
            link.href = href;
            document.head.appendChild(link);
            return link;
        });

        const scripts = [
            "/Libs/js/sidebar/bootstrap.min.js",
            "/Libs/js/sidebar/jquery.min.js",
            "/Libs/js/sidebar/main.js",
            "/Libs/js/sidebar/popper.js",
        ];

        const scriptElements = scripts.map(src => {
            const script = document.createElement("script");
            script.src = src;
            script.async = true;
            document.body.appendChild(script);
            return script;
        });

        return () => {
            styleElements.forEach(link => document.head.removeChild(link));
            scriptElements.forEach(script => document.body.removeChild(script));
        };

    }, []);

    return (
        <>

        <div class="wrapper d-flex align-items-stretch">
            <Aside />
        </div>
        
        </>
    );
};

export default Admin;
