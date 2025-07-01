//<![CDATA[
const sidebar = document.getElementById('sidebar');
const sidebarToggle = document.getElementById('sidebarToggle');
const mainContent = document.querySelector('.main-content'); // Optional

if (sidebar && sidebarToggle) {
    sidebarToggle.addEventListener('click', () => {
        sidebar.classList.toggle('sidebar-collapsed');

        const isCollapsed = sidebar.classList.contains('sidebar-collapsed');
        if (isCollapsed) {
            sidebarToggle.innerHTML = '→'; // Right Arrow
            sidebarToggle.setAttribute('aria-label', 'Expand Sidebar');
            sidebarToggle.setAttribute('aria-expanded', 'false');
        } else {
            sidebarToggle.innerHTML = '←'; // Left Arrow
            sidebarToggle.setAttribute('aria-label', 'Collapse Sidebar');
            sidebarToggle.setAttribute('aria-expanded', 'true');
        }
    });

    // Optional: Persist state using localStorage
    // const sidebarState = localStorage.getItem('sidebarState');
    // if (sidebarState === 'collapsed' && !sidebar.classList.contains('sidebar-collapsed')) {
    //    sidebarToggle.click(); // Simulate a click to collapse if needed
    // }
    // sidebarToggle.addEventListener('click', () => {
    //      localStorage.setItem('sidebarState', sidebar.classList.contains('sidebar-collapsed') ? 'collapsed' : 'expanded');
    // });
}
//]]>